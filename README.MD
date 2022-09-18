# REAL EXAMPLE OF KAFKA SPRING BOOT APPLICATION

# kafka-docker
consist of docker compose file
run it before starting other application

<br>
<code>> docker-compose up -d</code>


# GATEWAY
1. Send random taxi coordinate to COORDINATE-RECEIVER application
in scheduled time

# COORDINATE-RECEIVER
receive http post query and send it via kafka

# vehicle-distance
calculate vehicle distance

