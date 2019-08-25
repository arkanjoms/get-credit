#!/usr/bin/env bash
STACK_NAME="getcredit"
cd get-credit-stack
case "$1" in
    start)
        echo "Starting ${STACK_NAME} stack"
        docker-compose -p ${STACK_NAME} up -d --remove-orphans
        ;;
    stop)
        echo "Stopping ${STACK_NAME} stack"
        docker-compose -p ${STACK_NAME} stop
        ;;
    restart)
        echo "Restarting ${STACK_NAME} stack"
        docker-compose -p ${STACK_NAME} restart
        ;;
    clean)
        echo "Cleaning ${STACK_NAME} stack"
        docker-compose -p ${STACK_NAME} down -v
        ;;
    *)
        echo "Accepted commands: start, stop, restart or clean"
        ;;
esac
