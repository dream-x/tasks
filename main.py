from fastapi import FastAPI, HTTPException, Path, Query, Body, Depends
from fastapi.responses import FileResponse, JSONResponse, Response
from starlette.requests import Request
from pydantic import BaseModel
import sqlite3
import csv

app = FastAPI()

class Task(BaseModel):
    id: int
    title: str
    description: str

def init_db():
    conn = sqlite3.connect("tasks.db")
    cursor = conn.cursor()
    cursor.execute(
        "CREATE TABLE IF NOT EXISTS tasks (id INTEGER PRIMARY KEY, title TEXT, description TEXT)"
    )
    conn.commit()
    conn.close()

init_db()

@app.post("/tasks", status_code=201)
async def create_task(task: Task):
    conn = sqlite3.connect("tasks.db")
    cursor = conn.cursor()

    cursor.execute(
        "INSERT OR REPLACE INTO tasks (id, title, description) VALUES (?, ?, ?)",
        (task.id, task.title, task.description),
    )

    conn.commit()
    conn.close()

    headers = {
        "Location": f"/tasks/{task.id}"
    }

    return JSONResponse(content=task.dict(), status_code=201, headers=headers)

@app.get("/tasks/{task_id}", response_model=Task)
async def get_task_by_id(task_id: int = Path(...)):
    conn = sqlite3.connect("tasks.db")
    cursor = conn.cursor()

    cursor.execute("SELECT * FROM tasks WHERE id=?", (task_id,))
    result = cursor.fetchone()

    conn.close()

    if result:
        task = Task(id=result[0], title=result[1], description=result[2])
        return JSONResponse(content=task.dict())

    raise HTTPException(status_code=404, detail="Task not found")

@app.get("/tasks/all/csv")
async def get_all_tasks_csv(request: Request):
    conn = sqlite3.connect("tasks.db")
    cursor = conn.cursor()

    cursor.execute("SELECT * FROM tasks")
    result = cursor.fetchall()

    conn.close()

    with open("tasks.csv", "w", newline="") as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(["id", "title", "description"])
        writer.writerows(result)

    return FileResponse("tasks.csv", media_type="text/csv", headers={"Content-Disposition": "attachment; filename=tasks.csv"})

@app.put("/tasks/{task_id}")
async def update_task(
    task_id: int = Path(...),
    task: Task = Body(...),
):
    conn = sqlite3.connect("tasks.db")
    cursor = conn.cursor()

    cursor.execute(
        "UPDATE tasks SET title = ?, description = ? WHERE id = ?",
        (task.title, task.description, task_id),
    )
    conn.commit()
    conn.close()

    return JSONResponse(
        content={"status": "Task updated"},
        status_code=200,
        headers={
            "ETag": f'W/"{hash(str(task))}"'
        },
    )

@app.delete("/tasks/{task_id}")
async def delete_task(task_id: int = Path(...)):
    conn = sqlite3.connect("tasks.db")
    cursor = conn.cursor()

    cursor.execute("DELETE FROM tasks WHERE id = ?", (task_id,))
    conn.commit()
    conn.close()

    return JSONResponse(
        content={"status": f"Task {task_id} deleted"},
        status_code=200,
    )

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)

