package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class CheckedAppTest {

    @Test
    void checked(){
        Contoller contoller = new Contoller();
        Assertions.assertThatThrownBy(contoller::request)
                .isInstanceOf(Exception.class);
    }

    static class Contoller {
        Sercvice sercvice = new Sercvice();

        public void request() throws SQLException, ConnectException {
            sercvice.logic();
        }
    }
    static class Sercvice {
        Repository repository = new Repository();
        NetworkClient networkClient =  new NetworkClient();

        public void logic() throws SQLException, ConnectException {
            repository.call();
            networkClient.call();
        }

    }

    static class NetworkClient{
        public void call() throws ConnectException {
            throw new ConnectException("연결 실패");
        }
    }

    static class Repository{
        public void call() throws SQLException {
            throw new SQLException("ex");
        }
    }
}
