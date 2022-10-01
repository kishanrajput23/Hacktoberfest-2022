// selecting HTML code
const taskContainer = document.querySelector(".task_Container");

// Global Storage
let globalStore = [];
// newCard insertion  function
const newCard = ({ id, imageUrl, taskTitle, taskType, taskDescription }) => {
  return `<div class="col-md-6 col-lg-4 id=${id}">  
<div class="card m-lg-1">
    <div class="card-header d-flex justify-content-end gap-2">
        <button type="button" class="btn btn-outline-success"><i
                class="fas fa-pencil-alt"></i></button>
        <button type="button" class="btn btn-outline-danger"><i
                class="fas fa-trash-alt"></i></button>
    </div>
    <img src=${imageUrl}
        class="card-img-top p-2" alt="card img">
    <div class="card-body">
        <h5 class="card-title">${taskTitle}</h5>
        <p class="card-text">${taskDescription}
        </p>
        <span class="badge bg-primary mb-2">${taskType}</span></h </div>
        <div class="card-footer text-muted ">
            <button type="button" class="btn btn-outline-primary float-end rounded-pill">Open
                Task</button>
        </div>

    </div>
</div>`;
};

const loadInitialCardData = () => {
  //localstorage get tasky card data
  const getCardData = localStorage.getItem("tasky");          
  if(!getCardData)return;
  console.log(getCardData);
  //convert from string to normal object
  const { cards } = JSON.parse(getCardData);
  console.log(cards);
  //loop over these array of task object to create HTML  card,
  cards.map((cardObject) => {
    //inject it to DOM

    taskContainer.insertAdjacentHTML("beforeend", newCard(cardObject));

    //update our globalStore
    globalStore.push(cardObject);
  });
};

const saveChanges = () => {
  const taskData = {
    id: `${Date.now()}`,
    imageUrl: document.getElementById("imageUrl").value,
    taskTitle: document.getElementById("taskTitle").value,
    taskType: document.getElementById("taskType").value,
    taskDescription: document.getElementById("taskDescription").value,
  };

  taskContainer.insertAdjacentHTML("beforeend",newCard(taskData));

  globalStore.push(taskData);

  localStorage.setItem("tasky", JSON.stringify({ cards: globalStore })); //an object
};
/*
Issues
1.modal not closing after saving /adding data   -[solved]
2.the saved cards vanish/delete after refreshing page  -[solved]
3.Features
4.delete modal features
5.open task 
6.edit task
*/