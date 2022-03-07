import * as general from './general.js'

let showBodyMeasurement = "/show_body_measurement"
let updateBodyMeasurement = "/change_body_measurement"
let createBodyMeasurement = "/add_body_measurement"
let delBodyMeasurement = "/del_body_measurement"

document.querySelector(".show-volume").onclick = () => {
    showVolume()
}

const showVolume = () => {
    general.deleteGrab()
    general.postData(showBodyMeasurement, general.userId)
        .then((data) => {
            let table = document.createElement('table')
            table.innerHTML = `
        <tr>
            <th>Грудь</th>
            <th>Талия</th>
            <th>Бёдра</th>
            <th>Плечи</th>
            <th>Бицепс</th>
            <th>Икры</th>
        </tr>
            `
            table.classList.add('show-table')
            document.querySelector('.show-info').append(table)
            data.forEach(volume => {
                let volumeTr = document.createElement('tr');
                let buttonChange = document.createElement('button')
                let buttonDelete = document.createElement('button')
                volumeTr.classList.add(`tr-table${volume.id}`)
                volumeTr.innerHTML = `
                <td id="chest${general.userId}">${volume.chest}</td>
                <td id="waist${general.userId}">${volume.waist}</td>
                <td id="hips${general.userId}">${volume.hips}</td>
                <td id="shoulder${general.userId}">${volume.shoulder}</td>
                <td id="thigh${general.userId}">${volume.thigh}</td>
                <td id="calves${general.userId}">${volume.calves}</td>
                <td class="button-td${volume.id}"></td>
                <td class="buttondel-td${volume.id}"></td>
                `
                buttonChange.innerText = "Изменить"
                buttonDelete.innerText = "Удалить"
                document.querySelector('.show-table').append(volumeTr)
                document.querySelector(`.button-td${volume.id}`).append(buttonChange)
                document.querySelector(`.buttondel-td${volume.id}`).append(buttonDelete)
                buttonChange.onclick = () => changeVolume(volume)
                buttonDelete.onclick = () => deleteVolume(volume)
            })
        })
}

const deleteVolume = volume => {
    general.postData(delBodyMeasurement, volume.id)
    document.querySelector(`.tr-table${volume.id}`).remove()
}

const changeVolume = volume => {
    general.deleteGrab()
    let div = document.createElement('div')
    div.classList.add('div-add-volume')
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
    button.innerText = "Добавить"
    chest.value = `${volume.chest}`
    waist.value = `${volume.waist}`
    hips.value = `${volume.hips}`
    shoulder.value = `${volume.shoulder}`
    thigh.value = `${volume.thigh}`
    calves.value = `${volume.calves}`
    div.append(document.createElement('label').innerHTML = 'Замеры груди: ',
        chest, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры талии: ',
        waist, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры бёдер: ',
        hips, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры плеч: ',
        shoulder, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры бицепса: ',
        thigh, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры икр: ',
        calves, document.createElement('br'),
        document.createElement('br'))
    div.append(button)
    document.querySelector(`.show-info`).append(div)
    button.onclick = () => acceptChangeVolume(volume)
}

const acceptChangeVolume = volume => {
    volume.chest = document.querySelector(`.chest${general.userId}`).value
    volume.waist = document.querySelector(`.waist${general.userId}`).value
    volume.hips = document.querySelector(`.hips${general.userId}`).value
    volume.shoulder = document.querySelector(`.shoulder${general.userId}`).value
    volume.thigh = document.querySelector(`.thigh${general.userId}`).value
    volume.calves = document.querySelector(`.calves${general.userId}`).value
    general.postData(updateBodyMeasurement, volume)
    document.querySelector(".div-add-volume").remove()
}

document.querySelector(".add-volume").onclick = () => {
    general.deleteGrab()
    let div = document.createElement('div')
    div.classList.add('div-add-volume')
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
    button.innerText = "Добавить"
    div.append(document.createElement('label').innerHTML = 'Замеры груди: ',
        chest, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры талии: ',
        waist, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры бёдер: ',
        hips, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры плеч: ',
        shoulder, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры бицепса: ',
        thigh, document.createElement('br'),
        document.createElement('br'))
    div.append(document.createElement('label').innerHTML = 'Замеры икр: ',
        calves, document.createElement('br'),
        document.createElement('br'))
    div.append(button)
    document.querySelector(`.show-info`).append(div)
    button.onclick = () => addVolume()
}

const addVolume = () => {
    let chest = document.querySelector(`.chest${general.userId}`).value
    let waist = document.querySelector(`.waist${general.userId}`).value
    let hips = document.querySelector(`.hips${general.userId}`).value
    let shoulder = document.querySelector(`.shoulder${general.userId}`).value
    let thigh = document.querySelector(`.thigh${general.userId}`).value
    let calves = document.querySelector(`.calves${general.userId}`).value
    let bodyMeasurement = {
        chest: chest,
        waist: waist,
        hips: hips,
        shoulder: shoulder,
        thigh: thigh,
        calves: calves,
        userId: general.userId
    }
    general.postData(createBodyMeasurement, bodyMeasurement)
    document.querySelector(".div-add-volume").remove()
}
