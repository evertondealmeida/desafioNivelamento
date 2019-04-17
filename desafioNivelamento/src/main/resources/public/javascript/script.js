
function order(line) {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("consultbory");
  switching = true;
  while (switching) { 
    switching = false;
    rows = table.rows;
    for (i = 0; i < rows.length; i++) {
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[line];
      y = rows[i + 1].getElementsByTagName("TD")[line];
      if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}


