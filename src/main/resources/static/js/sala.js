window.addEventListener('load' , function() {
    validar();
    validarAdminSala();
});


async function validar() {
    let datos = {};
  
    datos.username = localStorage.getItem('email');

    const request = await fetch("/api/sala/verificar", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(datos),
    });
  
    const response = await request.text();
    if (response =="OK") {
      let link = '<li class="nav-item"><a class="nav-link " href="/registroSala" th:href="@{/registroSala}">Crear Sala</a></li>';
      
      document.getElementById("navbar-link").insertAdjacentHTML("beforeend", link);
      
    } else if(response == "USER"){
        let link ="";
        document.getElementById("navbar-link").outerHTML = link;
      
    }else{
        alert("HA OCURRIDO UN ERROR, VUELVA A INTENTAR O VUELVA A INICIAR SESION.");
    }
  }

  
  async function validarAdminSala(){
    let datos={}

    datos.id_admin = localStorage.getItem('id');

    const request = await fetch("/api/sala/verificarAdmin", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(datos),
    });

    const response = await request.text();
    if (response =="OK") {
      let link = '<a type="button" onclick="cerrarSala()" class="btn btn-danger">Cerrar Sala</a>';
      
      document.getElementById("form-post").insertAdjacentHTML("beforeend", link);
      
    }else if(response == "ERROR"){
        alert("HA OCURRIDO UN ERROR, VUELVA A INTENTAR O VUELVA A INICIAR SESION.");
    }

  }

  async function cerrarSala(){
    let datos={}

    datos.id = localStorage.getItem('salaId');

    const request = await fetch("/api/sala/cerrarSala", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(datos),
    });

    const response = await request.text();
    if (response =="OK") {
      
      document.location.reload();
       
    }else if(response == "ERROR"){
        alert("HA OCURRIDO UN ERROR, VUELVA A INTENTAR O VUELVA A INICIAR SESION.");
    }

  }

function almacenarId() {
    let salaId=document.getElementById("id").value;
    if(salaId != ""){
        localStorage.setItem("salaId",salaId)
        window.location.href = "index";
    }
    else{
        alert("ERROR, VUELVA INTENTAR...")
    }
    

  }

  function cerrarSesion(){
    localStorage.clear();
    window.location.href = "login"
}
