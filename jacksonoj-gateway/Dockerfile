# 基础镜像
FROM openjdk:8-jdk-alpine

# 指定工作目录
WORKDIR /app

# 将 jar 包添加到工作目录
ADD target/jacksonoj-gateway-0.0.1.jar .

# 暴露端口
EXPOSE 8204

# 启动命令
ENTRYPOINT ["java","-jar","/app/jacksonoj-gateway-0.0.1.jar","--spring.profiles.active=prod"]