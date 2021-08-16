FROM node:latest

RUN npm install -g npm@7.17.0

LABEL maintainer = "andjelapaunovic99@gmail.com"

EXPOSE 4200

WORKDIR /test
COPY . /test

 CMD npm start