window.onload = () => {
    fetch("/admin/checkRoot").then(r => r.json())
        .then(admin => {
            if (admin === true) {
                let div = document.createElement('div')
                div.innerHTML = `<br>
<div class="btn-group" role="group">
    <button id="btnGroupDrop4" type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown"
            aria-expanded="false">
        Админ панель
    </button>
    <ul class="dropdown-menu" aria-labelledby="btnGroupDrop1">
        <li><a class="dropdown-item show-all-users" href="#">Показать всех пользователей</a></li>
    </ul>
</div>
<br>`
                document.querySelector('.show-info').prepend(div)
            }
        })
}

export async function postData(url, data) {
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

export function deleteGrab() {
    try {
        document.querySelector('.show-table').remove()
    } catch (e) {
    }
    try {
        document.querySelector(".div-add").remove()
    } catch (e) {
    }
}