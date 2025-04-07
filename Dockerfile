# Etap budowania Angulara
FROM node:18 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

#run application
RUN npm run build --configuration=production


#USING ENGINE NGINX
FROM nginx:latest 
#copy engine configuration
COPY --from=build /app/dist/category_elements/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
# 

# the app will be using :80 while running
EXPOSE 80