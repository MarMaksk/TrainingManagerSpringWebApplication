import * as general from './general.js'

let showWeight = "/show_weight"
let updateWeight = "/change_weight"
let createWeight = "/add_weight"
let delWeight = "/del_weight"

document.querySelector(".show-weight").onclick = () => {
    showVolume()
}

const showVolume = () => {
    general.deleteGrab()
    fetch(showWeight)
        .then(response => response.json())
        .then((data) => {
            let table = document.createElement('table')
            table.innerHTML = `
        <tr>
            <th>Вес</th>
            <th>Дата измерения</th>
        </tr>
            `
            table.classList.add('show-table')
            document.querySelector('.show-info').append(table)
            data.forEach(weight => {
                let volumeTr = document.createElement('tr');
                let buttonChange = document.createElement('button')
                let buttonDelete = document.createElement('button')
                volumeTr.classList.add(`tr-table${weight.id}`)
                volumeTr.innerHTML = `
                <td id="chest">${weight.weight}</td>
                <td>${weight.date}</td>
                <td class="button-td${weight.id}"></td>
                <td class="buttondel-td${weight.id}"></td>
                `
                buttonChange.innerText = "Изменить"
                buttonDelete.innerText = "Удалить"
                document.querySelector('.show-table').append(volumeTr)
                document.querySelector(`.button-td${weight.id}`).append(buttonChange)
                document.querySelector(`.buttondel-td${weight.id}`).append(buttonDelete)
                buttonChange.onclick = () => changeWeight(weight)
                buttonDelete.onclick = () => deleteWeight(weight)
            })
        })
}

const deleteWeight = weight => {
    fetch(delWeight, weight.id)
    document.querySelector(`.tr-table${weight.id}`).remove()
}

const changeWeight = weight => {
    general.deleteGrab()
    let div = document.createElement('div')
    div.classList.add('div-add')
    let value = document.createElement('input')
    let button = document.createElement('button')
    value.classList.add(`weight`)
    value.type = "number"
    button.innerText = "Добавить"
    value.value = `${weight.weight}`
    div.append(document.createElement('label').innerHTML = 'Изменить вес: ',
        value, document.createElement('br'),
        document.createElement('br'))
    div.append(button)
    document.querySelector(`.show-info`).append(div)
    button.onclick = () => acceptChangeVolume(weight)
}

const acceptChangeVolume = weight => {
    weight.weight = document.querySelector(`.weight`).value
    general.postData(updateWeight, weight)
    document.querySelector(".div-add").remove()
}

document.querySelector(".add-weight").onclick = () => {
    general.deleteGrab()
    let div = document.createElement('div')
    div.classList.add('div-add')
    let weight = document.createElement('input')
    let button = document.createElement('button')
    weight.classList.add(`weight`)
    weight.type = "number"
    button.innerText = "Добавить"
    div.append(document.createElement('label').innerHTML = 'Измеренный вес: ',
        weight, document.createElement('br'),
        document.createElement('br'))
    div.append(button)
    document.querySelector(`.show-info`).append(div)
    button.onclick = () => addVolume()
}

const addVolume = () => {
    let value = document.querySelector(`.weight`).value
    let weight = {
        weight: value
    }
    general.postData(createWeight, weight)
    document.querySelector(".div-add").remove()
}
