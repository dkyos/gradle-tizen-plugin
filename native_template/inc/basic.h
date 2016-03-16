#ifndef __basic_H__
#define __basic_H__

#include <app.h>
#include <Elementary.h>
#include <system_settings.h>
#include <efl_extension.h>
#include <dlog.h>

#ifdef  LOG_TAG
#undef  LOG_TAG
#endif
#define LOG_TAG "basic"

#if !defined(PACKAGE)
#define PACKAGE "org.example.basic"
#endif

#endif /* __basic_H__ */
