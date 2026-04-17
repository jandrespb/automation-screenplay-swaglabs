# Automatización-screenplay-swaglabs

## Descripción

Proyecto de automatización de pruebas que utiliza Serenity BDD con el patrón Screenplay para la demo de Swag Labs. El objetivo principal de las pruebas incluidas es comprobar flujos básicos como login, selección de productos y eliminación de productos del carrito.

Además, el proyecto incluye un generador de reportes en Word que utiliza las evidencias (screenshots y pasos) generadas por Serenity.

## Funcionalidades principales

* Inicio de sesión (login) en la aplicación de Swag Labs.
* Selección de uno o varios productos.
* Eliminación de productos seleccionados del carrito.
* Reportes de ejecución generados por Serenity (HTML/JSON).
* Generación de reportes en Word (.docx) con evidencias reales de ejecución.

## Estructura relevante

* `src/test/resources/features/` : Features en Gherkin.
* `src/test/java/` : Implementación de steps, tasks, interactions, runners.
* `src/main/java/com/.../utils/report` : Lógica de generación de reportes (Word).
* `src/main/java/com/.../userinterface` o `config` : PageObjects y clases de configuración.
* `serenity.conf` / `serenity.properties` : Configuración de Serenity.

## Cómo identifica la página a abrir

Existen dos enfoques comunes en este proyecto:

1. @DefaultUrl en una clase PageObject

* Una clase que hereda de `PageObject` puede anotarse con `@DefaultUrl("https://...")`.
* Cuando el actor ejecuta `Open.browserOn(...)`, Serenity utiliza esa URL.

2. `serenity.conf` con entornos

* Permite manejar múltiples entornos (dev, qa, staging).
* Se ejecuta con:

```bash
./gradlew clean test -Dserenity.environment=qa
```

Recomendación:

* Usa `@DefaultUrl` para casos simples.
* Usa `serenity.conf` para múltiples entornos.

## Ejecución de pruebas y generación de reportes

### 🔹 Paso 1: Ejecutar automatización (Runner SwagLabs)

Este paso ejecuta las pruebas con Cucumber + Screenplay y genera los reportes de Serenity.

```bash
./gradlew clean test aggregate
```

Esto genera:

* Archivos JSON con resultados
* Screenshots de cada paso
* Reporte HTML

Ubicación:

```
target/site/serenity/
```

⚠️ Este paso es **obligatorio** para poder generar el Word.

---

### 🔹 Paso 2: Generar reporte en Word

Una vez finalizada la ejecución anterior, se debe ejecutar la clase:

```java
GenerateReportWord
```

Esta clase:

* Lee los archivos JSON generados por Serenity
* Filtra un escenario específico
* Extrae pasos e imágenes
* Genera un documento Word con evidencias

---

### ⚠️ Importante sobre el escenario

Para que el Word funcione correctamente:

* Debes indicar manualmente el nombre del escenario en el código:

```java
String scenario = "Remove element through shopping cart";
```

* Este nombre debe coincidir EXACTAMENTE con el definido en el feature:

```gherkin
Scenario Outline: Remove element through shopping cart
```

✔️ Correcto
❌ No usar nombres diferentes o inventados

---

### 📁 Ubicación del reporte Word

Los archivos Word se generan automáticamente en:

```
target/evidence-reports/
```

Cada ejecución genera un archivo único con timestamp:

```
Remove_element_through_shopping_cart_20260416_184512.docx
```

---

### 🧠 Cómo funciona el generador de Word

El flujo interno es:

1. Lee archivos `.json` desde:

   ```
   target/site/serenity
   ```

2. Filtra por nombre de escenario

3. Recorre los pasos de forma recursiva:

    * Solo toma nodos hoja (evita duplicados)

4. Extrae:

    * Descripción del paso
    * Screenshots asociados

5. Construye el documento Word con:

    * Tabla de información (escenario, autor, fecha)
    * Estado de ejecución (SUCCESS / FAILURE)
    * Evidencias paso a paso con imágenes

---

## Reportes

Serenity genera reportes en:

```
target/site/serenity
```

Abrir:

```
index.html
```

para visualizar el reporte completo.

## Buenas prácticas sobre credenciales y datos

* No incluir credenciales en features.
* Usar `data.properties` para datos reutilizables.
* Usar variables de entorno para datos sensibles.
* Utilizar `Examples:` para pruebas parametrizadas.

## Notas sobre diseño del código

* Separar configuración (`config` o `support`) de elementos UI (`userinterface`).
* Mantener PageObjects enfocados en interacción con la UI.
* Documentar clases base o eliminarlas si no aportan valor.

## Problemas comunes

* El Word no genera evidencias:
  → Verificar que se ejecutó primero `gradle clean test aggregate`.

* El Word trae información incorrecta:
  → Verificar que el nombre del escenario coincide exactamente.

* Imágenes duplicadas:
  → Resuelto mediante procesamiento de nodos hoja en el JSON.

* Archivo Word sobrescrito:
  → Solucionado con timestamp automático.

## Contacto / mantenimiento 💡

* Mantener sincronizados los nombres de escenarios entre feature y código.
* Revisar estructura JSON si se actualiza Serenity.
* Mantener dependencias actualizadas.

---

Sientete libre de configurar y mejorar el proyecto según tus necesidades. 
¡Buena suerte con tu automatización!

Jandtocode </>
