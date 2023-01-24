import React from "react";

const EditFrom = () => {
  return (
    <>
      <div className="container">
        <div className="py-5 text-center">
          <h2>상품 수정 폼</h2>
        </div>

        <form action="item.html" method="post">
          <div>
            <label>상품 ID</label>
            <input
              type="text"
              id="id"
              name="id"
              className="form-control"
              value="1"
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
            />
          </div>

          <div className="row">
            <div className="col">
              <button className="w-100 btn btn-primary btn-lg" type="submit">
                저장
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

export default EditFrom;
