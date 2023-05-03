async function registrar() {
    let datos = {};
  
    datos.nombre = document.getElementById("nombre").value;
    datos.id_admin = localStorage.getItem('id');

    const request = await fetch("/api/registroSala/post", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        'Authorization':localStorage.token
      },
      body: JSON.stringify(datos),
    });
  
    const response = await request.text();
    if (response !="FAIL") {
      
      window.location.href = "sala";
    } else {
      alert("HA OCURRIDO UN ERROR, VUELVA A INTENTAR O VUELVA A INICIAR SESION.");
    }
  }

  function cerrarSesion(){
    localStorage.clear();
    window.location.href = "login"
}



