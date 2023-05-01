FROM mysql:8.0.33

# Set the root password
ENV MYSQL_ROOT_PASSWORD=root

# Copy custom MySQL configuration file
COPY my.cnf /etc/mysql/conf.d/

# Create database and user
ENV MYSQL_DATABASE=usersdb
ENV MYSQL_USER=myuser
ENV MYSQL_PASSWORD=myuser

#Populate data
ADD setup.sql /docker-entrypoint-initdb.d/

# Expose port 3306 for MySQL
EXPOSE 3306