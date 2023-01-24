import React from "react";

const AddFrom = () => {
  return (
    <>
      <div className="container">
        <div className="py-5 text-center">
          <h2>상품 등록 폼</h2>
        </div>

        <form action="item.html" method="post">
          <div>
            <label>상품명</label>
            <input
              type="text"
              id="itemName"
              name="itemName"
              className="form-control"
              placeholder="이름을 입력하세요"
            />
          </div>
          <div>
            <label>가격</label>
            <input
              type="text"
              id="price"
              name="price"
              className="form-control"
              placeholder="가격을 입력하세요"
            />
          </div>
          <div>
            <label>수량</label>
            <input
              type="text"
              id="quantity"
              name="quantity"
              className="form-control"
              placeholder="수량을 입력하세요"
            />
          </div>

          <div className="row">
            <div className="col">
              <button className="w-100 btn btn-primary btn-lg" type="submit">
                상품 등록
              </button>
            </div>
            <div className="col">
              <button className="w-100 btn btn-secondary btn-lg" type="button">
                취소
              </button>
            </div>
          </div>
        </form>
      </div>
    </>
  );
};

export default AddFrom;
