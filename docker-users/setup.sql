/* Use the database createe in Dockerfile ENV MYSQL_DATABASE=usersdb*/
USE usersdb;

/* Give privileges to the created user in Dockerfile ENV MYSQL_USER=myuser*/
GRANT ALL PRIVILEGES ON * . * TO 'myuser'@'%';
FLUSH PRIVILEGES;


CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE roles (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE permissions (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE user_roles (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_roles_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_roles_roles FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    UNIQUE (user_id, role_id)
);

CREATE TABLE role_permissions (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id INT NOT NULL,
    permission_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_role_permissions_roles FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    CONSTRAINT fk_role_permissions_permissions FOREIGN KEY (permission_id) REFERENCES permissions (id) ON DELETE CASCADE,
    UNIQUE (role_id, permission_id)
);

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

