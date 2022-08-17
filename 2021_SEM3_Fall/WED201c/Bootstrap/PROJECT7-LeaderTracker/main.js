const inputEl = document.getElementById("input-el")
const saveInputBtn = document.getElementById("save-input-btn");
const saveTabBtn = document.getElementById("save-tab-btn");
const removeAllBtn = document.getElementById("remove-all-btn");
const listEl = document.getElementById("list-el");

let myLeads = [];
const leadsFromLocalStorage = JSON.parse(localStorage.getItem("myLeads"));

// load leads from local storage
// use JSON.parse(localStorage.getItem(arrName))

// save to local storage
// use localStorage.setItems("arrName", JSON.stringify(arrName))

if (leadsFromLocalStorage) {
    myLeads = leadsFromLocalStorage;
    render(myLeads);
}

function render(leads) {
    let listItems = "";
    for (let i = 0; i < leads.length; ++i) {
        listItems += `
        <li>
            <a target="_blank" href="${leads[i]}">
                ${leads[i]}
            </a>
        </li>`;
    }
    listEl.innerHTML = listItems;
}

saveInputBtn.addEventListener("click", function() {
    myLeads.push(inputEl.value);
    inputEl.value = "";
    localStorage.setItem("myLeads", JSON.stringify(myLeads));
    render(myLeads);
});

saveTabBtn.addEventListener("click", function() {
    chrome.tabs.query({ active: true, currentWindow: true }, function(tabs) {
        myLeads.push(tabs[0].url);
        localStorage.setItem("myLeads", JSON.stringify(myLeads));
        render(myLeads);
    })

});

removeAllBtn.addEventListener("click", function() {
    localStorage.clear();
    myLeads = [];
    render(myLeads);
});