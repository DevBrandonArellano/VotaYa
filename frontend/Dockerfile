# Etapa 1: Construcción
FROM node:18-alpine AS build
WORKDIR /app

# Instalar dependencias
COPY package*.json ./
RUN npm install

# Copiar código fuente y compilar
COPY . .
RUN npm run build

# --- CORRECCIÓN ---
# Aquí verificamos si Vite creó 'dist' o 'build' y lo renombramos
# a una carpeta estándar llamada 'sitio_listo'.
RUN sh -c '[ -d dist ] && mv dist sitio_listo || mv build sitio_listo'

# Etapa 2: Servidor Web (Nginx)
FROM nginx:alpine

# Ahora copiamos SIEMPRE desde 'sitio_listo', sin importar cómo se llamaba antes
COPY --from=build /app/sitio_listo /usr/share/nginx/html

# Configuración para que React Router funcione bien
RUN echo 'server { listen 80; root /usr/share/nginx/html; index index.html; location / { try_files $uri $uri/ /index.html; } }' > /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]