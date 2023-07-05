# spring-metrics
Project springboot for metrics with micrometer, prometheus and grafana

## run project
      mvn clean install
      run application

## run prometheus
docker run -p 9090:9090 -v /path/to/file/Prometheus/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

## start grafana server
sudo systemctl start grafana-server

## view metrics application
      http://localhost:8080/actuator/metrics
      
      http://localhost:8080/actuator/metrics/{name.metric}
      
      http://localhost:8080/actuator/prometheus
