import * as general from './general.js'

let showTrainings = "/show_training"
let updateTraining = "/change_training"
let muscleGroup = "/muscle_group"
let createTraining = "/add_training"
let delTraining = "/del_training"

document.querySelector(".show-training").onclick = () => {
    showData()
}

const showData = () => {
    general.deleteGrab()
    general.postData(showTrainings, general.userId)
        .then((data) => {
            let table = document.createElement('table')
            table.innerHTML = `
        <tr>
            <th>День</th>
            <th>Группа мышц</th>
            <th>Описание упражнения</th>
            <th>Подходы в последний раз</th>
            <th>Повторения в последний раз</th>
            <th>Вес в последний раз</th>
            <th>Дата последнего выполения</th>
        </tr>
            `
            table.classList.add('show-table')
            document.querySelector('.show-info').append(table)
            data.forEach(training => {
                let trainingTr = document.createElement('tr');
                let buttonChange = document.createElement('button')
                let buttonDelete = document.createElement('button')
                trainingTr.classList.add(`tr-table${training.id}`)
                trainingTr.innerHTML = `
                <td id="day${training.id}">${training.day}</td>
                <td>${training.muscleGroup}</td>
                <td id="descrip${training.id}">${training.descriptionExercises}</td>
                <td>${training.lastApproaches}</td>
                <td>${training.lastRepeats}</td>
                <td>${training.lastWeight}</td>
                <td>${training.lastDate}</td>
                <td class="button-td${training.id}"></td>
                <td class="buttondel-td${training.id}"></td>
                `
                buttonChange.innerText = "Изменить"
                buttonDelete.innerText = "Удалить"
                document.querySelector('.show-table').append(trainingTr)
                document.querySelector(`.button-td${training.id}`).append(buttonChange)
                document.querySelector(`.buttondel-td${training.id}`).append(buttonDelete)
                buttonChange.onclick = () => changeTraining(training)
                buttonDelete.onclick = () => deleteTraining(training)
            })
        })
}

const deleteTraining = training => {
    general.postData(delTraining, training)
    document.querySelector(`.tr-table${training.id}`).remove()
}

const changeTraining = training => {
    document.querySelector('.show-table').remove()
    let innerDay = document.createElement('input')
    let innerDescription = document.createElement('input')
    let button = document.createElement('button')
    button.classList.add('changeButton')
    innerDay.value = training.day
    innerDay.type = "number"
    innerDay.classList.add("innerDay" + `${training.id}`)
    innerDescription.value = training.descriptionExercises
    innerDescription.classList.add("innerDescription" + `${training.id}`)
    document.querySelector(`.show-info`).append(innerDay)
    document.querySelector(`.show-info`).append(innerDescription)
    document.querySelector(`.show-info`).append(button)
    let cont = document.querySelector(`.show-info`)
    cont.append(innerDay)
    cont.append(innerDescription)
    cont.append(button)
    button.innerText = "Подтвердить"
    button.onclick = () => acceptChanges(training)
}

const acceptChanges = training => {
    showData()
    let innerDay = document.querySelector(".innerDay" + `${training.id}`)
    let innerDesc = document.querySelector(".innerDescription" + `${training.id}`);
    training.day = innerDay.value
    training.descriptionExercises = innerDesc.value
    innerDay.remove()
    innerDesc.remove()
    document.querySelector('.changeButton').remove()
    general.postData(updateTraining, training)
}

document.querySelector(".add-training").onclick = () => {
    general.deleteGrab()
    let div = document.createElement('div')
    let innerDay = document.createElement('input')
    let innerDescription = document.createElement('input')
    let innerMuscle = document.createElement('input')
    let button = document.createElement('button')
    innerDay.classList.add(`day${general.userId}`)
    innerDay.type = "number"
    innerDescription.classList.add(`descrip${general.userId}`)
    innerMuscle.classList.add(`muscle${general.userId}`)
    button.classList.add(`button`)
    button.innerText = "Добавить"
    div.append(innerDay)
    div.append(innerDescription)
    div.append(document.createElement('br'))
    div.classList.add("div-add-training")
    fetch(muscleGroup)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            data.forEach(el => {
                let input = document.createElement('input')
                let label = document.createElement('label')
                input.classList.add(`add${general.userId}`)
                label.classList.add(`add${general.userId}`)
                input.name = "muscleGroup"
                input.type = "radio"
                input.value = el
                label.innerHTML = "<sp>" + el + "<sp>"
                div.append(input)
                div.append(label)
                div.append(document.createElement('br'))
            })
        });
    div.append(document.createElement('br'))
    div.append(button)
    document.querySelector(`.show-info`).append(div)
    button.onclick = () => addTraining()
}
const addTraining = () => {
    let radioButtons = document.getElementsByName('muscleGroup')
    let inputDay = document.querySelector(`.day${general.userId}`)
    let inputDescrip = document.querySelector(`.descrip${general.userId}`)
    let choise
    for (let i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].checked) {
            choise = radioButtons[i]
        }
    }
    let training = {
        day: inputDay.value,
        muscleGroup: choise.value,
        descriptionExercises: inputDescrip.value,
        userId: general.userId
    }
    general.postData(createTraining, training)
    document.querySelector(".div-add-training").remove()
}
