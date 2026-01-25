# automation-screenplay-swaglabs

Descripción
-----------
Proyecto de automatización de pruebas que utiliza Serenity BDD con el patrón Screenplay para la demo de Swag Labs. El objetivo principal de las pruebas incluidas es comprobar flujos básicos como login, selección de productos y eliminación de productos del carrito.

Funcionalidades principales
---------------------------
- Inicio de sesión (login) en la aplicación de Swag Labs.
- Selección de uno o varios productos.
- Eliminación de productos seleccionados del carrito.
- Reportes de ejecución generados por Serenity (HTML/JSON) al finalizar las pruebas.

Estructura relevante
--------------------
- `src/test/resources/features/` : Features en Gherkin.
- `src/test/java/` : Implementación de steps, tasks, interactions, runners.
- `src/main/java/com/.../userinterface` o `config` : PageObjects y clases de configuración (por ejemplo clases con `@DefaultUrl` o helpers de entorno).
- `serenity.conf` / `serenity.properties` : Configuración de Serenity y entornos.

Cómo identifica la página a abrir
-------------------------------
Existen dos enfoques comunes en este proyecto:

1) @DefaultUrl en una clase PageObject
- Una clase que hereda de `PageObject` puede anotarse con `@DefaultUrl("https://...")`.
- Cuando el actor ejecuta una task que hace `Open.browserOn(new MiPagina())` o se usa algún helper de Serenity para abrir la página, Serenity utiliza esa URL como predeterminada.
- Es una forma clara y local de fijar la URL por página.

2) `serenity.conf` con entornos
- Para manejar múltiples entornos (dev, qa, staging) es preferible usar `serenity.conf` y definir un bloque `environments { ... }` con la clave `base.url` u otra propiedad.
- La ejecución selecciona el entorno con la propiedad `-Dserenity.environment=qa` (o mediante perfiles de Serenity).
- Es más flexible cuando se necesitan múltiples entornos.

Recomendación práctica:
- Para una sola URL o para documentación rápida, `@DefaultUrl` en el PageObject está bien.
- Si vas a probar en varios entornos, define esas URLs en `serenity.conf` y ejecuta con `-Dserenity.environment=...`.

Ejecutar las pruebas
--------------------
Desde macOS (zsh) con el wrapper de Gradle incluido en el proyecto.

Ejecutar todas las pruebas:

```bash
./gradlew clean test
```

Ejecutar un runner concreto (por ejemplo `SwagLabRunner`):

```bash
./gradlew test --tests "com.swaglab.jandcode.runners.SwagLabRunner"
```

Ejecutar contra un entorno específico (usando `serenity.conf`):

```bash
./gradlew clean test -Dserenity.environment=qa
```

Ejecutar una feature concreta (ruta relativa a resources):

```bash
./gradlew clean test -Dserenity.features=src/test/resources/features/remove_product.feature
```

(Dependiendo de la versión de Gradle/Serenity puede variar la forma exacta de filtrar features; si no funciona, usar `--tests` apuntando al runner o a la clase de prueba deseada.)

Reportes
--------
Al completar la ejecución Serenity genera los reportes en formato HTML (y otros) en una carpeta como:

- `target/site/serenity` o `build/site/serenity` (revisa cuál se crea en tu build)

Abre el `index.html` dentro de esa carpeta para ver el reporte completo.

Buenas prácticas sobre credenciales y datos
-----------------------------------------
- Evita poner credenciales (usuario/contraseña) directamente en los archivos feature: las features deben ser legibles y centradas en comportamiento.
- Para datos reutilizables o no sensibles:
  - Usa un archivo `data.properties` o `test-data.properties` dentro de `src/test/resources`.
  - Carga propiedades desde el código de test o con la integración de Serenity.
- Para credenciales sensibles:
  - Usa variables de entorno o un vault/secret manager.
  - O usa perfiles de CI con secretos inyectados en tiempo de ejecución.
- Para pruebas dirigidas por ejemplos, usa tablas `Examples:` en las feature (Gherkin) para mantener claridad y trazabilidad.

Notas sobre diseño del código
----------------------------
- Las clases que contienen configuración (por ejemplo URLs por defecto, helpers de entorno) son razonables en una carpeta `config` o `support` en lugar de `userinterface` si no representan elementos UI. Eso mejora la claridad. Las PageObjects y selectores deberían quedar en `userinterface`.
- Una clase que solo hereda de `PageObject` y no tiene contenido puede estar si sirve como base común o para agrupar anotaciones (`@DefaultUrl`) — si no aporta, es mejor eliminarla o documentar su propósito.

Ejemplo rápido de escenario (Gherkin)
------------------------------------
Feature: Eliminar producto
  Scenario: Usuario inicia sesión, selecciona y elimina un producto
    Given que el usuario está en la página de login
    When inicia sesión con "standard_user" y "secret_sauce"
    And añade el producto "Sauce Labs Backpack" al carrito
    And va al carrito y elimina el producto
    Then el carrito debe estar vacío

Problemas comunes
-----------------
- NullPointer al eliminar `environments` y usar `@DefaultUrl`: puede ocurrir si existe código que asume la presencia de una propiedad de entorno; revisar dónde se lee `System.getProperty("serenity.environment")` o accesos a propiedades nulas.
- Si la página no abre, verificar: 1) que la clase PageObject tiene `@DefaultUrl` o que `serenity.conf` tiene la URL; 2) que las Tasks usan `Open.browserOn(...)` o la API de Serenity para abrir la página; 3) que el driver está correctamente configurado.

Contacto / mantenimiento
------------------------
- Si mueves clases entre carpetas (`userinterface` -> `config`) actualiza los paquetes y los imports.
- Mantén las URLs y credenciales fuera de los features por seguridad y claridad.



