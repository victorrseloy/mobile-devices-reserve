FROM node:8.11.3

WORKDIR /usr/app

COPY package.json ./

COPY yarn.lock ./

RUN yarn install

RUN npm rebuild node-sass --force

COPY . .

EXPOSE 3000

CMD ["yarn","start"]
