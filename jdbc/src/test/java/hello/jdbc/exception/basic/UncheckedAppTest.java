package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

@Slf4j
public class UncheckedAppTest {

    @Test
    void unchecked(){
        Contoller contoller = new Contoller();
        Assertions.assertThatThrownBy(contoller::request)
                .isInstanceOf(RuntimeSQLException.class);
    }

    @Test
    void printEx(){
        Contoller contoller = new Contoller();
        try{
            contoller.request();
        }catch (Exception e){
//        e.printStackTrace();
            log.info("ex", e);
        }
    }

    static class Contoller {
        Sercvice sercvice = new Sercvice();

        public void request() {
            sercvice.logic();
        }
    }
    static class Sercvice {
        Repository repository = new Repository();
        NetworkClient networkClient =  new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();
        }

    }

    static class NetworkClient{
        public void call() {
            throw new RuntimeConnectException("연결 실패");
        }
    }

    static class Repository{
        public void call() {
            try{
                runSQL();
            }catch (SQLException e){
                throw new RuntimeSQLException(e);
//                throw new RuntimeSQLException();
//                예외를 포함하지 않으면 기존에 무슨 예러인지 알기 어렵다
            }
        }

        public void runSQL() throws SQLException {
            throw new SQLException("ex");
        }
    }
    static class RuntimeConnectException extends RuntimeException{
        public RuntimeConnectException(String message){
            super(message);
        }
    }

    static class RuntimeSQLException extends RuntimeException {

        public RuntimeSQLException() {
        }

        public RuntimeSQLException(Throwable cause) {
            super(cause);
        }
    }
}
