package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * NamedParameterJdbcTemplate
 * SqlParameterSource
 * - BeanPropertySqlParameterSource
 * - MapSalParameterSource
 * <p>
 * Map
 * <p>
 * BeanPropertyRowMap
 */

@Slf4j
@Repository
public class JdbcTemplateItemRepositoryV2 implements ItemRepository {
//    private final JdbcTemplate template;

    private final NamedParameterJdbcTemplate template;

    public JdbcTemplateItemRepositoryV2(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public Item save(Item item) {
        String sql = "insert into item(item_name,price,quantity)" +
                " values (:itemName,:price,:quantity)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(item);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);


        long key = Objects.requireNonNull(keyHolder.getKey()).longValue();
        item.setId(key);
        return item;
    }


    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        String sql = "update item " +
                " set item_name=:itemName, price=:price, quantity=:quantity " +
                "where id=:id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("itemName", updateParam.getItemName())
                .addValue("price", updateParam.getPrice())
                .addValue("quantity", updateParam.getQuantity())
                .addValue("id", itemId);

        template.update(sql, param);
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "select id, item_name, price, quantity from item where id = :id";
        try {
            Map<String, Long> param = Map.of("id", id);
            Item item = template.queryForObject(sql, param, itemRowMapper());
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Item> findAll(ItemSearchCond condition) {
        String itemName = condition.getItemName();
        Integer maxPrice = condition.getMaxPrice();

        SqlParameterSource param = new BeanPropertySqlParameterSource(condition);

        String sql = "select id, item_name, price, quantity from item"; //동적 쿼리
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " where";
        }
        boolean andFlag = false;

        if (StringUtils.hasText(itemName)) {
            sql += " item_name like concat('%',:itemName,'%')";

            andFlag = true;
        }
        if (maxPrice != null) {
            if (andFlag) {
                sql += " and";
            }
            sql += " price <= :maxPrice";

        }


        return template.query(sql, param, itemRowMapper());
    }

    @Override
    public List<Item> findAllItems() {
        String sql = "select * from item";

        return template.query(sql, itemRowMapper());
    }

    private RowMapper<Item> itemRowMapper() {
        return BeanPropertyRowMapper.newInstance(Item.class);
    }


}
