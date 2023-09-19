# LeiaPraMim
> Aplicativo de acessibilidade para leitura de textos

O **LeiaPraMim** foi desenvolvido com um propósito fundamental: proporcionar acesso à informação para indivíduos que enfrentam desafios na leitura. Projetado especialmente para atender pessoas que não têm habilidades de leitura, o **LeiaPraMim** utiliza tecnologia avançada de Reconhecimento Óptico de Caracteres (OCR) e Text to Speech, para converter texto de imagens em palavras faladas.

[Download do Arquivo AAB](https://github.com/vandersonlb/LeiaPraMim/raw/main/app/release/app-release.aab)

## Tecnologia utilizada
- *Plataforma*: Android (Versão mínima: Android 8.1 "Oreo")
- *IDE*: Android Studio
- *Linguagem de Programação*: Kotlin com Jetpack Compose
- *Design System*: Material Design
- *Navegação*: Navigation Compose
- *Gerenciamento de Estado*: Lifecycle e LiveData
- *Banco de Dados*: Room
- *Requisições REST*: Retrofit2 e Gson

### APIs Externas Integradas

- [**Voice RSS**](https://www.voicerss.org/api/) - Text-to-Speech API
- [**OCR Space**](https://ocr.space/ocrapi) - Optical Character Recognition

## Melhorias previstas
- Cortar as fotos antes da "leitura"
- Melhorar o tratamento de erros
- Tocar o áudio com o erro recebido
