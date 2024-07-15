function enableForm() {
  // Najde všechny inputy ve formuláři
  var inputs = document.getElementById('userForm').getElementsByTagName('input');

  // Projde všechny inputy a nastaví jim atribut readonly na false, aby byly editovatelné
  for (var i = 0; i < inputs.length; i++) {
    inputs[i].readOnly = false;
  }
}