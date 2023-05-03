async function registrar() {
    let datos = {};
  
    datos.nombre = document.getElementById("nombre").value;
    datos.apellido = document.getElementById("apellido").value;
    datos.username = document.getElementById("username").value;
    datos.password = document.getElementById("password").value;
    const request = await fetch("/api/registroAdmin/post", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(datos),
    });
  
    const response = await request.text();
    if (response !="FAIL") {
      
      window.location.href = "login";
    } else {
      alert("ESE CORREO EXISTE, INGRESE OTRO POR FAVOR");
    }
  }
  

