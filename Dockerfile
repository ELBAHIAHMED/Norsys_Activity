#Create the node stage
FROM node:lastet as builder
#Set the working directory
WORKDIR /app
#Copy file from the current directory to working directory
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build --prod
FROM nginx:alpine
#Set the working directory to nginx assets directory
WORKDIR /usr/share/nginx/html
#Remove the default nginx static files
RUN rm -rf ./*
#Copy the static content from builder stage
COPY --from=node /app/dist/norsys-activity-client-side .
#Container run the nginx with global directive and Daemon off
ENTRYPOINT [ "nginx", "-g", "daemon off;" ]