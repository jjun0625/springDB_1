package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *  트랜잭션 - 파타미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accontTransfer(String fromId, String toId, int money) throws SQLException {
        Connection connection = dataSource.getConnection();
        try{
            connection.setAutoCommit(false);
            // 비즈니스 로직 수행

            bizLogic(connection, fromId, toId, money);

            connection.commit(); // 성공시 커밋
        }catch (Exception e){

            connection.rollback(); // 실패시 롤백
            throw new IllegalStateException(e);
        } finally {
            release(connection);
        }


    }

    private void bizLogic(Connection connection, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(connection, fromId);
        Member toMember = memberRepository.findById(connection, toId);

        memberRepository.update(connection, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(connection, toId, toMember.getMoney() + money);
    }

    private static void release(Connection connection) {
        if(connection != null){
            try{
                connection.setAutoCommit(true); //  connect pool 고려
                connection.close();
            }catch (Exception e) {
                log.info("error",e);
            }
        }
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
