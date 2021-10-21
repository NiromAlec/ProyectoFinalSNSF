insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Bogota");
insert into ciudad values (3, "Quimbaya");

insert into usuario values ("123", "hola123", "nikorodri@gmail.com", "Nicolas", 1);
insert into usuario values ("456", "hellou123", "camcam@gmail.com", "Camilo", 2);
insert into usuario values ("789", "salut123", "Serch@gmail.com", "Santiago", 3);

insert into administrador values ("7878","x1234","@123","Sara");
insert into administrador values ("4545", "y456","@456","Alexander");
insert into administrador values ("3535", "z789","@789","Niko");

insert into producto values ("111", "Lleno de potacio", 0.2, "2021/10/19", "Banano", 750.0, 10, 2, "123");
insert into producto values ("222", "Lleno de fibra", 0.3, "2021/10/20", "Carne", 5000.0, 5000, 1, "456");
insert into producto values ("333", "Lleno de hierro", 0.4, "2025/10/30", "Cereal", 8000.0, 5, 3, "789");

insert into producto_imagen values ("111", "http:/imagen.png", "banano");
insert into producto_imagen values ("222", "http:/imagen.png", "casa");
insert into producto_imagen values ("333", "http:/imagen99.png", "yogurt");

insert into comentario values ("a1", 5, "2021/10/19", "Hola, me ha interesado mucho tu producto", "gracias", "111", "123");
insert into comentario values ("a2", 5, "2021/10/19", "Hola, me ha interesado mucho tu producto", "gracias", "222", "123");
insert into comentario values ("a3", 5, "2021/10/19", "Hola, me ha interesado mucho tu producto", "gracias", "333", "123");

insert into categoria   values (1,"Tecnologia");
insert into categoria   values (2,"Deporte");
insert into categoria   values (3,"Libros");

insert into compra  values (1,"2021/05/06","CREDITO","123");
insert into compra  values (2,"2021/07/05","DEBITO","456");
insert into compra  values (3,"2021/08/03","EFECTIVO","789");

insert into detalle_compra  values ("569", 6000.0, 5,1, "111");
insert into detalle_compra  values ("785", 5000.0, 2,2, "222");
insert into detalle_compra  values ("432", 8000.0, 7,3, "333");

insert into usuario_telefonos values ("123", "3114012221","trabajo");
insert into usuario_telefonos values ("456", "3114012222","casa");
insert into usuario_telefonos values ("789", "3114012223","casa");

insert into chat values ("100", "123");
insert into chat values ("200", "456");
insert into chat values ("300", "789");

insert into mensaje values ("999", "niko", "2020/10/09", "...", "100");
insert into mensaje values ("787", "sergio", "2020/11/09", "...", "200");
insert into mensaje values ("090", "fer", "2020/12/09", "...", "300");

insert into subasta values ("4", "2021/03/11", "111");
insert into subasta values ("5", "2021/04/12", "222");
insert into subasta values ("6", "2021/11/12", "333");

insert into subasta_usuario values ("1", "2021/09/20", 50000.00, "4", "123");
insert into subasta_usuario values ("2", "2021/07/29", 60000.00, "5", "456");
insert into subasta_usuario values ("3", "2021/08/13", 80000.00, "6", "789");