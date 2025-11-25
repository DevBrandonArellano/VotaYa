# Etapa 1: Build de la aplicación de React
FROM node:20-alpine AS build
WORKDIR /app

# Copiar package.json y package-lock.json (o yarn.lock)
COPY package*.json ./
RUN npm install

# Copiar el resto del código de la aplicación
COPY . .
RUN npm run build

# Etapa 2: Servir la aplicación con Nginx
FROM nginx:stable-alpine
WORKDIR /usr/share/nginx/html

# Eliminar el contenido por defecto de Nginx
RUN rm -rf ./*

# Copiar los archivos de build desde la etapa anterior
COPY --from=build /app/dist .

# Copiar la configuración de Nginx para SPAs
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
