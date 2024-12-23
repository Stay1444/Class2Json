# Class2Json

Extracts basic metadata from java class files and spits out Json so it can be understood by other programming languages

```json
{
  "name": "f.Ac",
  "parentClass": "f.Zk0",
  "implementedInterfaces": [],
  "modifiers": [
    "Final",
    "Public"
  ],
  "fields": [
    {
      "name": "TN",
      "type": "J",
      "modifiers": [
        "Public"
      ]
    }
  ],
  "methods": [
    {
      "name": "zR",
      "returnType": "int",
      "modifiers": [
        "Static",
        "Public"
      ],
      "parameters": [
        "int",
        "int",
        "long",
        "int[]"
      ]
    }
  ]
}
```