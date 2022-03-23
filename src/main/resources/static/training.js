import * as general from './general.js'
import {deleteGrab, postData} from './general.js'

let showTrainings = "/show_training"
let startTrainings = "/start_training"
let updateTraining = "/change_training"
let muscleGroup = "/muscle_group"
let createTraining = "/add_training"
let delTraining = "/del_training"

let workoutSave = "/workout_save"

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
    let div = document.createElement('div')
    let innerDay = document.createElement('input')
    let innerDescription = document.createElement('input')
    let button = document.createElement('button')
    div.classList.add('div-add')
    button.classList.add('changeButton')
    innerDay.value = training.day
    innerDay.type = "number"
    innerDay.classList.add("innerDay" + `${training.id}`)
    innerDescription.value = training.descriptionExercises
    innerDescription.classList.add("innerDescription" + `${training.id}`)
    document.querySelector(`.show-info`).append(innerDay)
    document.querySelector(`.show-info`).append(innerDescription)
    document.querySelector(`.show-info`).append(button)
    div.append(innerDay)
    div.append(innerDescription)
    div.append(button)
    document.querySelector(`.show-info`).append(div)
    button.innerText = "Подтвердить"
    button.onclick = () => acceptChanges(training)
}

const acceptChanges = training => {
    showData()
    let innerDay = document.querySelector(".innerDay" + `${training.id}`)
    let innerDesc = document.querySelector(".innerDescription" + `${training.id}`);
    training.day = innerDay.value
    training.descriptionExercises = innerDesc.value
    general.postData(updateTraining, training)
    general.deleteGrab()
}

document.querySelector(".add-training").onclick = async () => {
    general.deleteGrab()
    let div = document.createElement('div')
    let innerDay = document.createElement('input')
    let innerDescription = document.createElement('input')
    let button = document.createElement('button')
    innerDay.classList.add(`day${general.userId}`)
    innerDay.type = "number"
    innerDescription.classList.add(`descrip${general.userId}`)
    button.innerText = "Добавить"
    div.append(document.createElement('label').innerText = "Номер тренировочного дня: ")
    div.append(innerDay)
    div.append(document.createElement('br'))
    div.append(document.createElement('label').innerText = "Описание тренировки: ")
    div.append(innerDescription)
    div.append(document.createElement('br'))
    div.classList.add("div-add")
    div.append(document.createElement('label').innerText = "Группа мышц: ")
    div.append(document.createElement('br'))
    await fetch(muscleGroup)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            data.forEach(el => {
                let input = document.createElement('input')
                let label = document.createElement('label')
                input.name = "muscleGroup"
                input.type = "radio"
                input.value = el
                label.innerHTML = "<sp>" + el + "<sp>"
                div.append(input)
                div.append(label)
                div.append(document.createElement('br'))
            })
        });
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
    document.querySelector(".div-add").remove()
}

document.querySelector(".start-training").onclick = async () => {
    general.deleteGrab()
    let div = document.createElement('div')
    let innerDay = document.createElement('input')
    let button = document.createElement('button')
    innerDay.type = "number"
    button.innerText = "Начать"
    innerDay.classList.add(`day${general.userId}`)
    div.append(innerDay)
    div.append(document.createElement('label').innerHTML = ' номер дня тренировки')
    div.append(document.createElement('br'))
    div.classList.add('div-add')
    div.append(document.createElement('label').innerHTML = 'Группа мышц:')
    div.append(document.createElement('br'))
    await fetch(muscleGroup)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            data.forEach(el => {
                let input = document.createElement('input')
                let label = document.createElement('label')
                input.name = "muscleGroup"
                input.type = "radio"
                input.value = el
                label.innerHTML = "<sp>" + el + "<sp>"
                div.append(input)
                div.append(label)
                div.append(document.createElement('br'))
            })
        });
    div.append(button)
    document.querySelector(`.show-info`).append(div)
    button.onclick = () => startTraining()
}

const startTraining = () => {
    let day = document.querySelector(`.day${general.userId}`)
    let radioButtons = document.getElementsByName('muscleGroup')
    let choise
    for (let i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].checked) {
            choise = radioButtons[i]
        }
    }
    let training = {
        day: day.value,
        muscleGroup: choise.value,
        userId: general.userId
    }
    general.deleteGrab()
    general.postData(startTrainings, training)
        .then((data) => {
            let table = document.createElement('table')
            table.innerHTML = `
        <tr>
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
                let button = document.createElement('button')
                trainingTr.classList.add(`tr-table${training.id}`)
                trainingTr.innerHTML = `
                <td id="descrip${training.id}">${training.descriptionExercises}</td>
                <td>${training.lastApproaches}</td>
                <td>${training.lastRepeats}</td>
                <td>${training.lastWeight}</td>
                <td>${training.lastDate}</td>
                <td class="button-td${training.id}"></td>
                `
                button.innerText = "Начать выполнение"
                document.querySelector('.show-table').append(trainingTr)
                document.querySelector(`.button-td${training.id}`).append(button)
                button.onclick = () => beginExercise(training)
            })
        })
}

const beginExercise = training => {
    general.deleteGrab()
    let div = document.createElement('div')
    let approaches = document.createElement('input')
    let repeats = document.createElement('input')
    let weight = document.createElement('input')
    let button = document.createElement('button')
    approaches.type = 'number'
    approaches.classList.add('approaches')
    repeats.type = 'number'
    repeats.classList.add('repeats')
    weight.type = 'number'
    weight.classList.add('weight')
    button.innerHTML = 'Подтвердить'
    div.classList.add('div-add')
    div.append(document.createElement('label').innerHTML = 'Число подходов:')
    div.append(approaches)
    div.append(document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Число повторений:')
    div.append(repeats)
    div.append(document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Используемый вес:')
    div.append(weight)
    div.append(document.createElement('br'))
    div.append(button)
    document.querySelector(`.show-info`).append(div)
    button.onclick = () => pasteTraining(training)
}

const pasteTraining = training => {
    let workout = {
        weight: document.querySelector('.weight').value,
        repeats: document.querySelector('.repeats').value,
        approaches: document.querySelector('.approaches').value,
        muscleGroup: training.muscleGroup,
        userId: general.userId,
        trainingId: training.id
    }
    deleteGrab()
    postData(workoutSave, workout)
}
