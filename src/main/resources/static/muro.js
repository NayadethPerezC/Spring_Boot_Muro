async function load_comments(event) {
    // 0. Evitar que el enlace dirija a otra página
    event.preventDefault();
    // 1. Recuperamos el ID del mensaje clickeado
    const message_id = event.target.dataset.id;
    console.log("cklickeado el enlace " + message_id);
    // 2. Realizamos la solicitud al servidor
    const response = await fetch('/api/messages/'+message_id+'/comments');
    // 3. Abrimos la respuesta
    const data = await response.json()
    // 4. Renderizamos los comentarios
    render_comments(message_id, data);
  }
  
  function render_comments(message_id, data) {
    const lista_comentarios = document.getElementById(message_id);
    for (const comment of data) {
      lista_comentarios.innerHTML += `
        <div class="comentarios px-2 m-2 border border-success">
          <h4>${comment.creatorsName}</h4>
          <p>${comment.content}</p>
        </div>
      `;
    }
    // hacemos la lista de comentarios visible
    console.log($('#' + message_id).html());
    $('#' + message_id).slideDown(1500);
  }
  
  function batman () {
    let str = '';
    for (let i=0; i<12; i++) {
      str += parseInt('hola')
    }
    console.log(str);
    console.log('LIIIIDEEEER');
  }
  
  async function add_like (event) {
    // 0. Evitamos que el enlace clickeado recargue la página
    event.preventDefault();
    // 1. Recuperamos el ID del mensaje clickeado
    const message_id = event.target.dataset.id;
    // 2. Realizamos la solicitud al servidor
    const response = await fetch('/messages/'+message_id+'/likes', {
      method: 'POST'
    });
    // 3. Actualizamos el número de likes
    update_likes (message_id); 
  }
  function update_likes(message_id) {
    // 1. Recuperamos el SPAN
    const span_likes = $('#likes-' + message_id);
    // 2. Calculamos el nuevo valor, sumandole 1 al valor antiguo
    const nuevo_valor = parseInt(span_likes.html()) + 1;
    // 3. Dejamos el nuevo valor en el SPAN
    span_likes.html(nuevo_valor)
  }