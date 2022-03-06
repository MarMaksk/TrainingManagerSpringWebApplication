import * as general from './general.js'

let showBodyMeasurement = "/show_body_measurement"
let updateBodyMeasurement = "/change_body_measurement"
let createBodyMeasurement = "/add_body_measurement"
let delBodyMeasurement = "/add_body_measurement"

document.querySelector(".show-volume").onclick = () => {
    showVolume()
}

const showVolume = () => {
    try {
        document.querySelector('.show-table').remove()
    } catch (e) {
    }
    try {
        document.querySelector(".div-add-training").remove()
    } catch (e) {
    }
    general.postData(showBodyMeasurement, general.userId)
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
                identification++
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
                trainingTr.id = `${training.id}`
                trainingTr.classList.add('training-element')
                buttonChange.innerText = "Изменить"
                //buttonChange.id = `${identification}button`
                buttonDelete.innerText = "Удалить"
                //document.querySelector('.show-table').classList.remove("hidden")
                document.querySelector('.show-table').append(trainingTr)
                document.querySelector(`.button-td${training.id}`).append(buttonChange)
                document.querySelector(`.buttondel-td${training.id}`).append(buttonDelete)
                buttonChange.onclick = () => changeVolume(training)
                buttonDelete.onclick = () => deleteVolume(training)
            })
        })
}

const deleteVolume = training => {
    general.postData(delBodyMeasurement, training)
    document.querySelector(`.tr-table${training.id}`).remove()
}

const changeVolume = training => {
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

const acceptChangeVolume = training => {
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

document.querySelector(".add-volume").onclick = () => {
    console.log(general.userId)
    try {
        document.querySelector('.show-table').remove()
    } catch (e) {
    }
    let div = document.createElement('div')
    let label = document.createElement('label')
    let chest = document.createElement('input')
    let waist = document.createElement('input')
    let hips = document.createElement('input')
    let shoulder = document.createElement('input')
    let thigh = document.createElement('input')
    let calves = document.createElement('input')
    let button = document.createElement('button')
    chest.classList.add(`chest${general.userId}`)
    waist.classList.add(`waist${general.userId}`)
    hips.classList.add(`hips${general.userId}`)
    shoulder.classList.add(`shoulder${general.userId}`)
    thigh.classList.add(`thigh${general.userId}`)
    calves.classList.add(`calves${general.userId}`)
    chest.type = "number"
    waist.type = "number"
    hips.type = "number"
    shoulder.type = "number"
    thigh.type = "number"
    calves.type = "number"
    button.classList.add(`button-add-body`)
    button.innerText = "Добавить"
    div.append(document.createElement('label').innerHTML = 'Замеры груди: ',
        chest, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры талии: ')
    div.append(waist)
    div.append(document.createElement('br'))
    div.classList.add("div-add-volume")
    div.append(document.createElement('br'))
    div.append(button)
    document.querySelector(`.show-info`).append(div)
    button.onclick = () => addVolume()
}

const addVolume = () => {
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
        userId: id
    }
    postData(createTraining, training)
    document.querySelector(".div-add-training").remove()
}
