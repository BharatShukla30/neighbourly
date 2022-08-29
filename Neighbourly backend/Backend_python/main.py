from fastapi import FastAPI
import pyrebase

app = FastAPI()


@app.get("/hello")
async def root():
    return {"message": "Hello World"}