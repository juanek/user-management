$(document).ready(function () {
  $("#menu-toggle").click(function () {
    if ($("#icon-toggle").hasClass("fa fa-chevron-left")) {
      $("#icon-toggle").removeClass("fa fa-chevron-left");
      $("#icon-toggle").addClass("fa fa-bars");
    } else if ($("#icon-toggle").hasClass("fa fa-bars")) {
      $("#icon-toggle").removeClass("fa fa-bars");
      $("#icon-toggle").addClass("fa fa-chevron-left");
    }

    $("#wrapper").toggleClass("toggled");
  });
});