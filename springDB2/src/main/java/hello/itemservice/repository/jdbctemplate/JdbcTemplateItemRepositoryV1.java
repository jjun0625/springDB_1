package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * JdbcTemplate
 */

@Slf4j
@Repository
public class JdbcTemplateItemRepositoryV1 implements ItemRepository {
    private final JdbcTemplate template;

    public JdbcTemplateItemRepositoryV1(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }


    @Override
    public Item save(Item item) {
        String sql = "insert into item(item_name,price,quantity) values (?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
        // 자동 증가 키
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1,item.getItemName());
            preparedStatement.setInt(2, item.getPrice());
            preparedStatement.setInt(3, item.getQuantity());
            return preparedStatement;

        }, keyHolder);

        long key = Objects.requireNonNull(keyHolder.getKey()).longValue();
        item.setId(key);
        return item;
    }


    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        String sql = "update item set item_name=?, price=?, quantity=? where id=?";
        template.update(sql,
                updateParam.getItemName(),
                updateParam.getPrice(),
                updateParam.getQuantity(),
                itemId
        );
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "select id, item_name, price, quantity from item where id = ?";
        try{
            Item item = template.queryForObject(sql, itemRowMapper(), id);
            return Optional.of(item);
        } catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }



    @Override
    public List<Item> findAll(ItemSearchCond condition) {
        String itemName = condition.getItemName();
        Integer maxPrice = condition.getMaxPrice();
        String sql = "select id, item_name, price, quantity from item"; //동적 쿼리
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " where";
        }
        boolean andFlag = false;
        List<Object> param = new ArrayList<>();
        if (StringUtils.hasText(itemName)) {
            sql += " item_name like concat('%',?,'%')";
            param.add(itemName);
            andFlag = true;
        }
        if (maxPrice != null) {
            if (andFlag) {
                sql += " and";
            }
            sql += " price <= ?";
            param.add(maxPrice);
        }
        log.info("sql={}", sql);

        return template.query(sql, itemRowMapper(), param.toArray());
    }

    @Override
    public List<Item> findAllItems() {
        String sql = "select * from item";

        return template.query(sql, itemRowMapper());
    }

    private RowMapper<Item> itemRowMapper() {
        return (((rs, rowNum) -> {
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setItemName(rs.getString("item_name"));
            item.setPrice(rs.getInt("price"));
            item.setQuantity(rs.getInt("quantity"));
            return item;
        }));
    }


}
