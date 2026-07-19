# infra — Yerel altyapi (Podman)

PostgreSQL 17 + pgAdmin. Her mikroservis kendi veritabanini kullanir
(database-per-service): `productdb`, `customerdb`, `orderdb`.

## Kurulum

```bash
cd infra
cp .env.example .env        # sifreleri degistirmek isterseniz duzenleyin
podman compose up -d        # veya: podman-compose up -d
```

Ilk calistirmada `init/01-init-databases.sh` uc veritabanini olusturur.

## Kontrol

```bash
podman ps
podman exec -it deneme-postgres psql -U deneme -c '\l'
```

- Postgres: `localhost:5432`, kullanici `deneme` / sifre `deneme123`
- pgAdmin: http://localhost:5050 (`admin@deneme.local` / `admin`)

## Durdurma

```bash
podman compose down          # container'lari siler, veri kalir
podman compose down -v       # veriyi de siler (init script yeniden calisir)
```

## Servis baglantisi

Config'ler `configs/{servis}/application.yml` icinde. Varsayilanlari
ortam degiskeniyle ezebilirsiniz:

| Degisken      | Varsayilan                                  |
|---------------|---------------------------------------------|
| `DB_URL`      | `jdbc:postgresql://localhost:5432/<db>`     |
| `DB_USERNAME` | `deneme`                                    |
| `DB_PASSWORD` | `deneme123`                                 |

Eski H2 kurulumuna donmek icin: `SPRING_PROFILES_ACTIVE=h2`

> Not: `init/` klasoru yalnizca postgres-data volume'u bosken calisir.
> Script'i degistirdiyseniz `podman compose down -v` ile sifirlayin.

> Not: `compose.yaml` icindeki `name: denememikro` satirini silmeyin.
> Compose proje adini varsayilan olarak klasor adindan ("infra") turetir ve
> bu makinedeki baska bir "infra" yiginin volume'leriyle cakisir.

> Not: Servisler config'i config-service uzerinden **GitHub master**'dan ceker.
> `configs/` altindaki degisiklikler push edilmeden servislere ulasmaz.
