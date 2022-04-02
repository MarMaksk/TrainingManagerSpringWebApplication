import * as general from "./general";

let getAllUsers = "/get_all_users"

document.querySelector(".show-all-users").onclick = () => {
    general.deleteGrab()
    fetch(getAllUsers)
        .then(response => response.json())
        .then((data) => {
            let table = document.createElement('table')
            table.innerHTML = `
        <tr>
            <th>id</th>
            <th>Имя пользователя</th>
            <th>Текущий вес</th>
        </tr>
            `
            table.classList.add('show-table')
            document.querySelector('.show-info').append(table)
            data.forEach(user => {
                let userTr = document.createElement('tr');
                userTr.innerHTML = `
                <td>${user.id}</td>
                <td>${user.nickname}</td>
                <td>${user.currentWeight}</td>
                `
                document.querySelector('.show-table').append(userTr)
            })
        })
}