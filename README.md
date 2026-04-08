# utez-2e-biblioteca-javafx-equipo07
Proyecto integrador — Aplicaciones de Escritorio / POO  
Tecnología: Java + JavaFX (FXML + Controller)  
Grupo: 2°E

---

## Descripción
Sistema de escritorio desarrollado en JavaFX para gestionar el catálogo de una biblioteca escolar. Permite registrar, consultar, editar y eliminar libros, con persistencia local en archivo CSV para mantener los datos entre ejecuciones.

---

## Funcionalidades principales
- **Alta:** Registro de libros mediante formulario con validaciones.
- **Consulta:** Visualización del catálogo completo en TableView interactiva.
- **Actualización:** Edición de libros existentes con carga automática de datos.
- **Eliminación:** Baja de libros con confirmación previa.
- **Detalle:** Vista completa de los datos de un libro seleccionado.
- **Exportar reporte:** Genera el archivo `reporte_catalogo.csv` con todos los libros del catálogo actual.

---

## Pasos de ejecución

### Requisitos previos
- Java 21 o superior instalado
- Maven instalado (o usar el wrapper `mvnw` incluido)
- JavaFX 21 (se descarga automáticamente con Maven)

### Cómo ejecutar
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/utez-2e-biblioteca-javafx-equipo07.git
   cd utez-2e-biblioteca-javafx-equipo07
   ```
2. Ejecutar con Maven:
   ```bash
   mvn javafx:run
   ```
   O desde el IDE (IntelliJ IDEA / Eclipse): abrir el proyecto como proyecto Maven y ejecutar la clase `Launcher.java`.

3. Al iniciar, la aplicación carga automáticamente los libros desde `data/books.csv`.

---

## Persistencia en archivo

El sistema guarda todos los datos en el archivo `data/books.csv`, ubicado en la raíz del proyecto.

- **Formato:** cada línea representa un libro con los campos separados por coma:  
  `ID,Titulo,Autor,Año,Genero,Disponible`
- **Carga:** al iniciar la aplicación, `BooksService` lee el archivo línea por línea, crea objetos `Libro` y los muestra en la tabla.
- **Guardado:** cada operación (agregar, editar, eliminar) sobreescribe el archivo completo a través de `BooksFileRepository`, garantizando que los datos persistan al cerrar y volver a abrir la app.
- **Sin base de datos:** toda la persistencia es local, sin dependencias externas.

---

## Reporte exportado

Al presionar el botón **"Exportar reporte"**, el sistema genera el archivo `reporte_catalogo.csv` en el directorio de ejecución del proyecto.

- **Contenido:** todos los libros actualmente en el catálogo, incluyendo su estado de disponibilidad.
- **Formato:** encabezado + una línea por libro con todos sus campos separados por coma.
- **Clase responsable:** `ReportExport.java` (paquete `model`), método `exportar(List<Libro> libros)`.

---

## Estructura del proyecto
```
src/main/java/.../
├── model/
│   ├── Libro.java           # Modelo de datos
│   └── ReportExport.java    # Lógica de exportación
├── repositories/
│   └── BooksFileRepository.java  # Lectura/escritura del CSV
├── services/
│   └── BooksService.java    # Lógica de negocio y validaciones
└── controllers/
    ├── AppControllers.java       # Pantalla principal
    ├── FormularioController.java # Pantalla formulario (nuevo/editar)
    └── DetalleController.java    # Pantalla detalle
```

---

## Flujo de trabajo (Git Flow)
1. Cada integrante trabaja y hace commits en su rama personal.
2. Cuando una parte funciona, se hace merge a `dev` y se resuelven conflictos.
3. Se prueba que todo corra correctamente en `dev`.
4. Al finalizar, se hace merge de `dev` a `main` para la entrega.

---

## Integrantes
| Nombre | Apellido | Usuario Git |
|--------|----------|-------------|
| Lian   | Ramirez  | 20253ds124-collab |
| Marina | Flores   | 20253ds141MR |
