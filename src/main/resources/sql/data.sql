
/*Populate data*/

INSERT INTO users (username, password, email)
VALUES
('usuario1', 'usuario1', 'usuario1@mail.com'),
('usuario2', 'usuario2', 'usuario2@mail.com'),
('usuario3', 'usuario3', 'usuario3@mail.com');

INSERT INTO roles (name, description)
VALUES
('admin', 'Rol de administrador con permisos completos.'),
('editor', 'Rol de editor con permisos limitados.'),
('invitado', 'Rol de invitado con acceso limitado.');

INSERT INTO permissions (name, description)
VALUES
('create_post', 'Permiso para crear publicaciones.'),
('edit_post', 'Permiso para editar publicaciones.'),
('delete_post', 'Permiso para eliminar publicaciones.');

INSERT INTO user_roles (user_id, role_id)
VALUES
((SELECT id FROM users WHERE username = 'usuario1'), (SELECT id FROM roles WHERE name = 'admin'));

INSERT INTO role_permissions (role_id, permission_id)
VALUES
((SELECT id FROM roles WHERE name = 'editor'), (SELECT id FROM permissions WHERE name = 'create_post')),
((SELECT id FROM roles WHERE name = 'editor'), (SELECT id FROM permissions WHERE name = 'edit_post'));
