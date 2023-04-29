export interface Categoria {
    id: string;
    nombre: string;
    descripcion: string;
    obras: Obra[];
  }

  
export interface Obra {
    id: string;
    name: string;
    precio: number;
    titulo: string;
    img: string;
    estado: string;
    fecha: string;
    estilo: string;
    imaginero: Imaginero;
  }
  
  export interface Imaginero {
    id: string;
    name: string;
    edad: number;
    localidad: string;
  }
  