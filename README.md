# 🍽️ FoodFX - Tu Aplicación de Gestión de Restaurante

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/JavaFX-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="JavaFX">
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven">
</div>

## 🌟 Descripción

FoodFX es una aplicación de escritorio moderna y elegante desarrollada con JavaFX para la gestión integral de restaurantes. Diseñada con una interfaz de usuario intuitiva y funcionalidades robustas, esta aplicación te ayudará a gestionar tu negocio de manera eficiente.

## ✨ Características Principales

- 🎨 Interfaz de usuario moderna y responsiva
- 📊 Gestión de pedidos en tiempo real
- 🍳 Control de inventario
- 💰 Gestión de facturación
- 📈 Reportes y estadísticas

## 🚀 Requisitos del Sistema

- Java JDK 17 o superior
- JavaFX SDK
- Maven 3.6 o superior

## 🛠️ Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/Rediaj04/FoodFX.git
```

2. Navega al directorio del proyecto:
```bash
cd FoodFX
```

3. Compila el proyecto:
```bash
mvn clean install
```

4. Ejecuta la aplicación:
```bash
mvn javafx:run
```

## 📁 Estructura del Proyecto

```
FoodFX/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── puig/
│       │           ├── controllers/     # Controladores de la interfaz de usuario
│       │           │   └── MainController.java
│       │           ├── models/         # Modelos de datos
│       │           │   ├── Comida.java
│       │           │   └── Pedido.java
│       │           └── Main.java       # Punto de entrada de la aplicación
│       └── resources/                  # Recursos de la aplicación
│           └── com/
│               └── puig/
│                   ├── styles.css      # Estilos CSS de la aplicación
│                   ├── views/          # Archivos FXML para las vistas
│                   └── images/         # Imágenes y recursos gráficos
└── pom.xml                            # Configuración de Maven
```

## 🤝 Contribuciones

¡Las contribuciones son bienvenidas! Por favor, sigue estos pasos:

1. Haz un Fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 👥 Autores

- **Rediaj04** - *Desarrollo inicial* - [Rediaj04](https://github.com/Rediaj04)

## 🙏 Agradecimientos

- A todos los contribuidores que han ayudado a hacer este proyecto realidad
- A la comunidad de JavaFX por su excelente documentación y recursos

---
<div align="center">
  Hecho con ❤️ por el equipo de FoodFX
</div> 