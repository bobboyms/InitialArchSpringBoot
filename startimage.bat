docker build --no-cache -t nov13 -f nov11 .
docker run -v /var/run/docker.sock:/var/run/docker.sock nov12