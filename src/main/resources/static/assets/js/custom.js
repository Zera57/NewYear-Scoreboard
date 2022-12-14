function LinkFormatter(value, row, index) {
    return "<a href='"+row.url+"'>"+value+"</a>";
  }

  // Modal open and edit
  jQuery(document).ready(function($) {
    $('#example').DataTable({
        searching: false,
        responsive: true,
        paging: false,
        info: false,
        "autoWidth": false,
    });
    var table = $('#example').DataTable();
    $('#example tbody').on('click', 'tr', function () {
        console.log(table.row(this).data());
        $(".modal-body div span").text("Add points");
        $(".username span").text(table.row(this).data()[2]);
        $(".position span").text(table.row(this).data()[3]);
        $("#myModal").modal("show");
    });
} );
