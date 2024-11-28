import uvicorn
from fastapi import FastAPI, Depends, HTTPException, UploadFile
from sqlalchemy.orm import Session
from typing import List
import json
from sqlalchemy import desc
from starlette.middleware.cors import CORSMiddleware

# db.py에서 필요한 항목 가져오기
from db import SessionLocal, Product, init_db

# FastAPI 인스턴스 생성
app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 모든 도메인에서 접근 허용
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
# 데이터베이스 초기화
init_db()


def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


# 크롤링한 데이터를 데이터베이스에 저장하는 엔드포인트
@app.post("/upload-json/")
async def upload_json(file: UploadFile, db: Session = Depends(get_db)):
    try:
        # JSON 파일을 읽어 파싱
        data = json.loads(await file.read())

        # 각 제품 데이터를 데이터베이스에 저장
        for product_data in data:
            product = Product(
                name=product_data["name"],
                image_url=product_data["image_url"],
                price=product_data["price"],
                description=product_data["description"],
                url=product_data["url"],
                rating=product_data["rating"]
            )
            db.add(product)
        db.commit()

        return {"message": "Products saved successfully", "count": len(data)}

    except json.JSONDecodeError:
        raise HTTPException(status_code=400, detail="Invalid JSON format")


@app.get("/", response_model=List[dict])
def read_products(db: Session = Depends(get_db)):
    # rating 기준으로 내림차순 정렬
    products = db.query(Product).order_by(desc(Product.rating)).all()
    return [
        {
            "id": product.id,
            "name": product.name,
            "image_url": product.image_url,
            "price": product.price,
            "description": product.description,
            "url": product.url,
            "rating": product.rating,
        }
        for product in products
    ]


@app.get("/{name}", response_model=List[dict])
def read_products_by_name(name: str, db: Session = Depends(get_db)):
    products = db.query(Product).filter(Product.name.contains(name)).order_by(desc(Product.rating)).all()

    return [
        {
            "id": product.id,
            "name": product.name,
            "image_url": product.image_url,
            "price": product.price,
            "description": product.description,
            "url": product.url,
            "rating": product.rating,
        }
        for product in products
    ]


if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)
