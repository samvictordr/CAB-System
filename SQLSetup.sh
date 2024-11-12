#!/bin/bash

# run_sql_scripts.sh
# Script to execute all SQL scripts in the db/scripts directory against a MySQL Docker container

# Configuration
MYSQL_CONTAINER_NAME="CABMain"       # Replace with your MySQL Docker container name
DATABASE_NAME="CABS"                       # Replace with your target database name
MYSQL_USER="root"                            # Replace with your MySQL username
MYSQL_PASSWORD="rootpassword"                # Replace with your MySQL password
SCRIPTS_DIR="db/scripts"                     # Directory containing your SQL scripts

# Function to check if Docker container is running
check_container_running() {
    if ! docker ps --format '{{.Names}}' | grep -w "$MYSQL_CONTAINER_NAME" > /dev/null; then
        echo "Error: Docker container '$MYSQL_CONTAINER_NAME' is not running."
        exit 1
    fi
}

# Function to execute a single SQL script
execute_sql_script() {
    local script_path=$1
    echo "Executing $script_path..."
    docker exec -i "$MYSQL_CONTAINER_NAME" mysql -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$DATABASE_NAME" < "$script_path"

    if [ $? -ne 0 ]; then
        echo "Error: Failed to execute $script_path"
        exit 1
    else
        echo "Successfully executed $script_path"
    fi
}

# Main Execution
check_container_running

# Iterate over each SQL script in the scripts directory
for sql_file in "$SCRIPTS_DIR"/*.sql; do
    execute_sql_script "$sql_file"
done

echo "All SQL scripts have been executed successfully."