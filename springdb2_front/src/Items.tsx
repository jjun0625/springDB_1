import React from "react";

const Items = () => {
  return (
    <>
      <div className="container">
        <div className="py-5 text-center">
          <h2>상품 목록</h2>
        </div>

        {/* <div className="row">
        <div className="col">
            <button className="btn btn-primary float-end"
                    onclick="location.href='addForm.html'"
                    th:onclick="|location.href='@{/items/add}'|"
                    type="button">상품 등록
            </button>
        </div>
    </div> */}

        <form method="get" className="form-inline">
          <div className="row">
            <div className="col">
              <input
                type="text"
                className="form-control"
                placeholder="상품명"
              />
            </div>
            <div className="col">
              <input
                type="text"
                className="form-control"
                placeholder="가격제한"
              />
            </div>
            <div className="col">
              <button type="submit" className="btn btn-secondary mb-2">
                검색
              </button>
            </div>
            <div className="col"></div>
            <div className="col">
              <div className="col">
                <button className="btn btn-primary float-end" type="button">
                  상품 등록
                </button>
              </div>
            </div>
          </div>
        </form>

        <div>
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>상품명</th>
                <th>가격</th>
                <th>수량</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                {/* <td><a href="item.html" th:href="@{/items/{itemId}(itemId=${item.id})}" th:text="${item.id}">회원id</a>
                </td>
                <td><a href="item.html" th:href="@{|/items/${item.id}|}" th:text="${item.itemName}">상품명</a></td>
                <td th:text="${item.price}">10000</td>
                <td th:text="${item.quantity}">10</td> */}
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default Items;
