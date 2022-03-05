let showTrainings = "/show_training"
let updateTraining = "/change_training"
let muscleGroup = "/muscle_group"
let id = document.getElementById("userId").value
let identification = 0;
let tableClasses = document.querySelector('.show-table').classList

async function postData(url, data) {
    // Default options are marked with *
    const response = await fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omitSS
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
    return await response.json(); // parses JSON response into native JavaScript objects
}

document.querySelector(".show-training").onclick = () => {
    showData()
}

const showData = () => {
    postData(showTrainings, id)
        .then((data) => {
            data.forEach(training => {
                identification++
                let trainingTr = document.createElement('tr');
                let buttonChange = document.createElement('button')
                trainingTr.classList.add("tr-table")
                trainingTr.innerHTML = `
                <td id="day${identification}">${training.day}</td>
                <td>${training.muscle}</td>
                <td id="descrip${identification}">${training.descriptionExercises}</td>
                <td>${training.lastApproaches}</td>
                <td>${training.lastRepeats}</td>
                <td>${training.lastWeight}</td>
                <td>${training.lastDate}</td>
                <td class="button-td${identification}"></td>
                `
                trainingTr.id = `${identification}`
                trainingTr.classList.add('training-element')
                buttonChange.innerText = "Изменить"
                buttonChange.id = `${identification}button`
                tableClasses.remove("hidden")
                document.querySelector('.show-table').append(trainingTr)
                document.querySelector(`.button-td${identification}`).append(buttonChange)
                buttonChange.onclick = () => changeTraining(training)
            })
        })
}

const changeTraining = training => {
    tableClasses.add("hidden")
    let innerDay = document.createElement('input')
    let innerDescription = document.createElement('input')
    let button = document.createElement('button')
    button.classList.add('changeButton')
    innerDay.value = training.day
    innerDay.type = "number"
    innerDay.classList.add("innerDay" + `${training.id}`)
    innerDescription.value = training.descriptionExercises
    innerDescription.classList.add("innerDescription" + `${training.id}`)
    document.querySelector(`.show`).append(innerDay)
    document.querySelector(`.show`).append(innerDescription)
    document.querySelector(`.show`).append(button)
    button.innerText = "Подтвердить"
    button.onclick = () => acceptChanges(training)
}

const acceptChanges = training => {
    let innerDay = document.querySelector(".innerDay" + `${training.id}`)
    let innerDesc = document.querySelector(".innerDescription" + `${training.id}`);
    training.day = innerDay.value
    training.descriptionExercises = innerDesc.value
    innerDay.remove()
    innerDesc.remove()
    document.querySelector('.changeButton').remove()
    postData(updateTraining, training)
    console.log(training)
    document.querySelector("#day" + `${training.id}`).innerHTML = training.day
    document.querySelector("#descrip" + `${training.id}`).innerHTML = training.descriptionExercises
    tableClasses.remove("hidden")
}

document.querySelector(".add-training").onclick = () => {
    while (identification !== 0) {
        document.querySelector(".tr-table").remove()
    }
    tableClasses.add("hidden")
    let innerDay = document.createElement('input')
    let innerDescription = document.createElement('input')
    let innerMuscle = document.createElement('input')
    let button = document.createElement('button')
    innerDay.classList.add(`day${id}`)
    innerDescription.classList.add(`descrip${id}`)
    innerMuscle.classList.add(`muscle${id}`)
    button.classList.add(`button`)
    fetch(muscleGroup)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            showContacts(data)
        });
}