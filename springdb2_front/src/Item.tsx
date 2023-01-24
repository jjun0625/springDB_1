import React from "react";

const Item = () => {
  return (
    <>
      <div className="container">
        <div className="py-5 text-center">
          <h2>상품 상세</h2>
        </div>

        <div>
          <label>상품 ID</label>
          <input
            type="text"
            id="itemId"
            name="itemId"
            className="form-control"
            value="1"
            readOnly
          />
        </div>
        <div>
          <label>상품명</label>
          <input
            type="text"
            id="itemName"
            name="itemName"
            className="form-control"
            value="상품A"
            readOnly
          />
        </div>
        <div>
          <label>가격</label>
          <input
            type="text"
            id="price"
            name="price"
            className="form-control"
            value="10000"
            readOnly
          />
        </div>
        <div>
          <label>수량</label>
          <input
            type="text"
            id="quantity"
            name="quantity"
            className="form-control"
            value="10"
            readOnly
          />
        </div>

        <div className="row">
          <div className="col">
            <button className="w-100 btn btn-primary btn-lg" type="button">
              상품 수정
            </button>
          </div>
          <div className="col">
            <button className="w-100 btn btn-secondary btn-lg" type="button">
              목록으로
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Item;
