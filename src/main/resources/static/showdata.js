let showTrainings = "/show_training"
let updateTraining = "/change_training"
let id = document.getElementById("userId").value
let identification = 0;

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
    showTraining()
}

const showTraining = () => {
    postData(showTrainings, id)
        .then((data) => {
            data.forEach(training => {
                let trainingDiv = document.createElement('div');
                trainingDiv.innerHTML = `
                Номер дня тренировки: ${training.day} <br/>
                Группа мышц: ${training.muscle} <br/>
                Описание упражнения: ${training.descriptionExercises}  <br/>
                Число подходов выполненное в последний раз: ${training.lastApproaches} <br/> 
                Число повторений выполненное в последний раз: ${training.lastRepeats} <br/>
                Последний вес на упражнении: ${training.lastWeight} <br/> 
                Дата последнего выполнения упражнения: ${training.lastDate} <br/>`
                trainingDiv.classList.add('training-element')
                identification++
                trainingDiv.id = `${identification}`
                let buttonChange = document.createElement('button')
                buttonChange.innerText = "Изменить"
                buttonChange.id = `${identification}button`
                let line = document.createElement('div')
                line.innerHTML = '------------------------------------------------------------------`'
                document.querySelector('.show').append(trainingDiv)
                document.querySelector('.show').append(buttonChange)
                document.querySelector('.show').append(line)
                buttonChange.onclick = () => changeTraining(training)
            })
        })
}

const changeTraining = training => {
    let innerDay = document.createElement('input')
    let innerDescription = document.createElement('input')
    innerDay.value = training.day
    innerDay.type = "number"
    innerDay.classList.add("innerDay" + `${training.id}`)
    innerDescription.value = training.descriptionExercises
    innerDescription.classList.add("innerDescription" + `${training.id}`)
    document.getElementById(`${training.id}`).append(innerDay)
    document.getElementById(`${training.id}`).append(innerDescription)
    let button = document.getElementById(`${training.id}button`)
    button.innerText = "Подтвердить"
    button.onclick = () => acceptChanges(training)
}

const acceptChanges = training => {
    training.day = document.querySelector(".innerDay" + `${training.id}`).value
    training.descriptionExercises = document.querySelector(".innerDescription" + `${training.id}`).value
    postData(updateTraining, training)
    document.getElementById(`${training.id}`).innerHTML = `
                Номер дня тренировки: ${training.day} <br/>
                Группа мышц: ${training.muscle} <br/>
                Описание упражнения: ${training.descriptionExercises}  <br/>
                Число подходов выполненное в последний раз: ${training.lastApproaches} <br/> 
                Число повторений выполненное в последний раз: ${training.lastRepeats} <br/>
                Последний вес на упражнении: ${training.lastWeight} <br/> 
                Дата последнего выполнения упражнения: ${training.lastDate} <br/>`
    let button = document.getElementById(`${training.id}button`)
    button.innerText = "Изменить"
    console.log(training)
}