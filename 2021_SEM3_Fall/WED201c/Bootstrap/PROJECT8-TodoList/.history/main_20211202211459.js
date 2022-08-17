let myList = [];
const inputEl = document.querySelector("#input-item");
const listEl = document.querySelector("#todo-list");
const addBtn = document.querySelector("#add-btn");
const removeAllBtn = document.querySelector("#rm-all-btn");
const removeOneBtn = document.querySelector(".icon");
const markDone = document.querySelector(".mark-done");
const cntTask = document.querySelector("#pending-tasks");

const todoListFromLocalStorage = JSON.parse(localStorage.getItem("myList"));


if (todoListFromLocalStorage) {
    myList = todoListFromLocalStorage;
    render(myList);
}

function render(list) {
    let todoList = "";
    for (let i = 0; i < list.length; ++i) {
        todoList += `
        <li class="mark-done">
            ${list[i]}<span class="icon" onclick="removeOne(${i})"><i class="fas fa-trash"></i></span>
        </li>`;
    }
    listEl.innerHTML = todoList;
    cntTask.innerHTML = list.length;

}

function addNewTodo() {
    if (inputEl.value) {
        myList.push(inputEl.value);
        inputEl.value = "";
        localStorage.setItem("myList", JSON.stringify(myList));
        render(myList);
    }
}


inputEl.addEventListener("keyup", (event) => {
    if (event.key == "Enter")
        addNewTodo();

})

addBtn.addEventListener("click", () => { addNewTodo() });

removeAllBtn.addEventListener("dblclick", () => {
    myList = [];
    render(myList);
    localStorage.clear();
});

function removeOne(index) {
    myList.splice(index, 1);
    render(myList);
    localStorage.setItem("myList", JSON.stringify(myList));
}
